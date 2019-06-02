package _8.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import static _8.io.SonState.*;

public class SonWriter implements Closeable, Flushable {

	private static final String nextIndicator = ",";
	private final Writer out;
	private String indent;
	private String keyValueSeparator;

	private SonState[] stack = new SonState[32];
	private int stackTop = 0;
	private String nextName;

	public SonWriter(Writer out) {
		this.out = out;
		this.push(EMPTY_DOCUMENT);
		this.setIndent("  ");
	}

	public final void setIndent(String indent) {
		if(indent == null || indent.length() == 0) {
			this.indent = null;
			this.keyValueSeparator = ":";
		} else {
			this.indent = indent;
			this.keyValueSeparator = ": ";
		}
	}

	public SonWriter beginObject() throws IOException {
		this.open(EMPTY_OBJECT, "{");
		return this;
	}

	public SonWriter endObject() throws IOException {
		this.close(EMPTY_OBJECT, NONEMPTY_OBJECT, "}");
		return this;
	}

	public SonWriter beginArray() throws IOException {
		this.open(EMPTY_ARRAY, "[");
		return this;
	}

	public SonWriter endArray() throws IOException {
		this.close(EMPTY_ARRAY, NONEMPTY_ARRAY, "]");
		return this;
	}

	private void open(SonState empty, String openBracket) throws IOException {
		this.writeName();
		this.prepareValue();
		this.push(empty);
		this.out.write(openBracket);
	}

	private void close(SonState empty, SonState nonempty, String closedBracket) throws IOException {
		SonState state = this.peek();
		if(state != empty && state != nonempty) {
			throw new IllegalStateException("Can't close what's not open");
		}
		if(this.nextName != null) {
			throw new IllegalStateException("Dangling name: " + this.nextName);
		}

		this.pop();
		if(state == nonempty) {
			this.newline();
		}
		this.out.write(closedBracket);
	}

	public SonWriter name(String name) {
		if(name == null) {
			throw new NullPointerException("name == null");
		}
		if(this.nextName != null) {
			throw new IllegalStateException("Didn't expect a name again");
		}

		this.nextName = name;
		return this;
	}

	public SonWriter value(String value) throws IOException {
		this.writeName();
		this.prepareValue();
		this.out.write(value);
		return this;
	}

	public SonWriter value(Boolean value) throws IOException {
		return this.value(value ? "true" : "false");
	}

	public SonWriter value(Number value) throws IOException {
		return this.value(value.toString());
	}

	private void writeName() throws IOException {
		if(this.nextName != null) {
			this.prepareName();
			this.out.write(this.nextName);
			this.nextName = null;
		}
	}

	private void prepareName() throws IOException {
		SonState state = this.peek();
		if(state == NONEMPTY_OBJECT) { // not first in an object
			this.out.write(nextIndicator);
		} else if(state != EMPTY_OBJECT) { // not in an object
			throw new IllegalStateException("Didn't expect this to be named");
		}

		this.newline();
		this.replaceTop(DANGLING_NAME);
	}

	private void newline() throws IOException {
		if(this.indent == null) {
			return;
		}
		this.out.write("\n");
		for(int i = 1; i < this.stackTop; i++) {
			this.out.write(this.indent);
		}
	}

	private void prepareValue() throws IOException {
		SonState state = this.peek();
		switch(state) {
			case EMPTY_DOCUMENT:
				this.replaceTop(NONEMPTY_DOCUMENT);
				break;
			case EMPTY_ARRAY:
				this.replaceTop(NONEMPTY_ARRAY);
				this.newline();
				break;
			case NONEMPTY_ARRAY:
				this.out.write(nextIndicator);
				this.newline();
				break;
			case DANGLING_NAME:
				this.out.write(this.keyValueSeparator);
				this.replaceTop(NONEMPTY_OBJECT);
				break;
			default:
				throw new IllegalStateException();
		}
	}

	@Override
	public void flush() throws IOException {
		if(this.stackTop == 0) {
			throw new IllegalStateException("SonWriter already closed");
		}
		this.out.flush();
	}

	@Override
	public void close() throws IOException {
		this.out.close();
		if(this.stackTop > 1 || this.stackTop == 1 && this.peek() != NONEMPTY_DOCUMENT) {
			throw new IOException("Incomplete document");
		}
		this.stackTop = 0;
	}

	/* stack management */

	private SonState peek() {
		return this.stack[this.stackTop - 1];
	}

	private SonState pop() {
		return this.stack[--this.stackTop];
	}

	private void push(SonState newTop) {
		if(this.stackTop >= this.stack.length) {
			this.stack = Arrays.copyOf(this.stack, this.stackTop * 2);
		}
		this.stack[this.stackTop++] = newTop;
	}

	private void replaceTop(SonState newTop) {
		this.stack[this.stackTop - 1] = newTop;
	}
}
