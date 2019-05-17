package _6._3;

import java.util.*;
import provided._6._3.Company;

public class StarkEnterprises implements Company {
    private Map<Integer, String> employee = new HashMap<>();
    private Map<Integer, String> projects = new HashMap<>();
    private Map<Integer, Set<Integer>> projectsPerEmployee = new HashMap<>();

    @Override
    public void addEmployee(int id, String name) throws DuplicateIdException {
        if (employee.containsKey(id)) {
            throw new DuplicateIdException();
        }
        employee.put(id, name);
    }

    @Override
    public String getEmployeeName(int id) {
        return employee.get(id);
    }

    @Override
    public void addProject(int id, String name) throws DuplicateIdException {
        if (projects.containsKey(id)) {
            throw new DuplicateIdException();
        }
        projects.put(id, name);
    }

    @Override
    public String getProjectName(int id) {
        return projects.get(id);
    }

    @Override
    public void assignEmployeeToProject(int employeeId, int projectId) throws UnknownIdException {
        if (!employee.containsKey(employeeId)) {
            throw new UnknownIdException("Employee does not exist", employeeId);
        }

        if (!projects.containsKey(projectId)) {
            throw new UnknownIdException("Project does not exist", projectId);
        }

        projectsPerEmployee.computeIfAbsent(employeeId, HashSet::new).add(projectId);
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) throws UnknownIdException {
        if (!employee.containsKey(employeeId)) {
            throw new UnknownIdException("Employee does not exist", employeeId);
        }

        if (!projects.containsKey(projectId)) {
            throw new UnknownIdException("Project does not exist", projectId);
        }

        if (projectsPerEmployee.containsKey(employeeId)) {
            projectsPerEmployee.get(employeeId).remove(projectId);
        }
    }

    public Collection<Integer> getEmployees() {
        return collectKeysSortedByValue(employee, new EntryValueComparator<>());
    }

    private class EntryValueComparator<T extends Comparable<T>> implements Comparator<Map.Entry<?, T>> {
        @Override
        public int compare(Map.Entry<?, T> o1, Map.Entry<?, T> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    public Collection<Integer> getProjectsForEmployee(int employeeId) throws UnknownIdException {
        if (!employee.containsKey(employeeId)) {
            throw new UnknownIdException("Employee does not exist", employeeId);
        }

        Set<Integer> projectIds = projectsPerEmployee.get(employeeId);
        Map<Integer, String> projectsById = new HashMap<>();
        for (Integer projectId : projectIds) {
            projectsById.put(projectId, projects.get(projectId));
        }
        return collectKeysSortedByValue(projectsById, new EntryValueComparator<>());
    }

    private static <K, V> Collection<K> collectKeysSortedByValue(Map<K, V> map, Comparator<Map.Entry<?, V>> comparator) {
        //return map.entrySet().stream()
        //        .sorted(comparator)
        //        .map(Map.Entry::getKey)
        //        .collect(Collectors.toList());
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(comparator);
        List<K> ids = new ArrayList<>();
        for (Map.Entry<K, V> entry : entryList) {
            ids.add(entry.getKey());
        }
        return ids;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer employeeId : this.getEmployees()) {
            sb.append(this.employee.get(employeeId)).append('[').append(employeeId).append("]: ");
            StringJoiner sj = new StringJoiner(" ");
            for (Integer projectId : this.projectsPerEmployee.get(employeeId)) {
                sj.add(this.projects.get(projectId) + "[" + projectId + "]");
            }
            sb.append(sj.toString()).append('\n');
        }
        return sb.toString();
    }
}
