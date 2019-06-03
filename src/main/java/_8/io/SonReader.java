package _8.io;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

import static _8.io.SonState.*;

public class SonReader implements Closeable {

	private final Reader in;

	private SonState[] stack = new SonState[32];
	private int stackTop = 0;

	private Integer rememberedChar;

	public SonReader(Reader in) {
		this.in = in;
		this.pushState(EMPTY_DOCUMENT);
	}

	public void beginObject() throws IOException {
		this.prepareValue();
		if(this.nextNonWhitespace() == '{') {
			this.pushState(EMPTY_OBJECT);
		} else {
			throw new IllegalStateException("Expected BEGIN_OBJECT");
		}
	}

	public void endObject() throws IOException {
		if(this.nextNonWhitespace() == '}') {
			this.popState();
		} else {
			throw new IllegalStateException("Expected END_OBJECT");
		}
	}

	public void beginArray() throws IOException {
		this.prepareValue();
		if(this.nextNonWhitespace() == '[') {
			this.pushState(EMPTY_ARRAY);
		} else {
			throw new IllegalStateException("Expected BEGIN_ARRAY");
		}
	}

	public void endArray() throws IOException {
		if(this.nextNonWhitespace() == ']') {
			this.popState();
		} else {
			throw new IllegalStateException("Expected END_ARRAY");
		}
	}

	public boolean hasNext() throws IOException {
		int c = this.nextNonWhitespace();
		this.rememberChar(c);
		switch(this.peekState()) {
			case EMPTY_DOCUMENT:
			case EMPTY_OBJECT:
			case NONEMPTY_DOCUMENT:
			case NONEMPTY_OBJECT:
				return c != '}';
			case EMPTY_ARRAY:
			case NONEMPTY_ARRAY:
				return c != ']';
			case DANGLING_NAME:
			default:
				throw new IllegalStateException();
		}
	}

	public String nextName() throws IOException {
		return this.readName();
	}

	public String nextValue() throws IOException {
		return this.readValue();
	}

	private String readName() throws IOException {
		this.prepareName();

		final StringBuilder name = new StringBuilder();
		int c = this.nextNonWhitespace();
		do {
			name.append((char) c);
		} while((c = this.eofRead()) != ':');
		return name.toString();
	}

	private void prepareName() throws IOException {
		SonState state = this.peekState();
		if(state == NONEMPTY_OBJECT) { // not first in an object
			this.nextChar(true);// remove , from previous value
		} else if(state != EMPTY_OBJECT) { // not in an object
			throw new IllegalStateException("Didn't expect this to be named");
		}

		this.replaceTopState(DANGLING_NAME);
	}

	private void prepareValue() throws IOException {
		SonState state = this.peekState();
		switch(state) {
			case EMPTY_DOCUMENT:
				this.replaceTopState(NONEMPTY_DOCUMENT);
				break;
			case EMPTY_ARRAY:
				this.replaceTopState(NONEMPTY_ARRAY);
				break;
			case NONEMPTY_ARRAY:
			case NONEMPTY_OBJECT:
				this.nextChar(true); // remove , from previous element
				break;
			case DANGLING_NAME:
				this.replaceTopState(NONEMPTY_OBJECT);
				break;
			default:
				throw new IllegalStateException();
		}
	}

	private String readValue() throws IOException {
		this.prepareValue();
		final StringBuilder value = new StringBuilder();
		int c = this.nextNonWhitespace();
		do {
			value.append((char) c);
		} while((c = this.eofRead()) != ',' && c != '}' && c != ']');
		this.rememberChar(c);
		return value.toString().trim();
	}

	public void skipValue() throws IOException {
		this.prepareValue();
		int count = 0;
		int c;
		do {
			c = this.nextNonWhitespace();
			if(c == '{') {
				this.pushState(EMPTY_OBJECT);
				count++;
			} else if(c == '[') {
				this.pushState(EMPTY_ARRAY);
				count++;
			} else if(c == '}') {
				this.popState();
				count--;
			} else if(c == ']') {
				this.popState();
				count--;
			} else {
				this.nextValue();
			}
		} while(count != 0);
	}

	@Override
	public void close() throws IOException {
		this.in.close();
		this.stackTop = 1;
		this.stack[0] = CLOSED;
	}

	/* character stream management */

	private int nextNonWhitespace() throws IOException {
		int read;
		do {
			read = this.nextChar(true);
		} while(read == ' ' || read == '\t' || read == '\n');
		return read;
	}

	private int nextChar(boolean remembered) throws IOException {
		if(this.rememberedChar != null) {
			if(remembered) {
				int top = this.rememberedChar;
				this.rememberedChar = null;
				return top;
			} else {
				this.rememberedChar = null;
			}
		}
		return eofRead();
	}

	private int eofRead() throws IOException {
		int read = this.in.read();
		if(read == -1) {
			throw new EOFException();
		}
		return read;
	}

	private void rememberChar(int c) {
		if(this.rememberedChar != null) {
			throw new IllegalStateException("Already remembering a char");
		}
		this.rememberedChar = c;
	}

	/* state management */

	private SonState peekState() {
		return this.stack[this.stackTop - 1];
	}

	private SonState popState() {
		return this.stack[--this.stackTop];
	}

	private void pushState(SonState newTop) {
		if(this.stackTop >= this.stack.length) {
			this.stack = Arrays.copyOf(this.stack, this.stackTop * 2);
		}
		this.stack[this.stackTop++] = newTop;
	}

	private void replaceTopState(SonState newTop) {
		this.stack[this.stackTop - 1] = newTop;
	}
}
