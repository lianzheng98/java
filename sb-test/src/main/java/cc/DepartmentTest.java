package cc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class DepartmentTest {
    public static void main(String[] args) {
        List<Department> allDepartment = new ArrayList<>();
        Department dep1 = new Department(1, 0, "北京总部");
        Department dep3 = new Department(3, 1, "研发中心");
        Department dep4 = new Department(4, 3, "后端研发组");
        Department dep6 = new Department(6, 4, "后端实习生组");
        Department dep7 = new Department(7, 3, "前端研发组");
        Department dep8 = new Department(8, 1, "产品部");

        allDepartment.add(dep6);
        allDepartment.add(dep7);
        allDepartment.add(dep8);
        allDepartment.add(dep1);
        allDepartment.add(dep3);
        allDepartment.add(dep4);


        List<Department> subDepartments = DepartmentTest.getSub(3, allDepartment);
        for (Department subDepartment : subDepartments) {
            System.out.println(subDepartment);
        }
    }

    /**
     * 根据id，获取所有子部门列表(包括隔代子部门，一直到叶子节点)
     * 要求：不能新增参数，不能增加static变量
     *
     * @param id
     * @return
     */
    public static List<Department> getSub(int id, List<Department> allDepartment) {
        Map<Integer, Integer> id2levelMap = new HashMap<>();

        Map<Integer, List<Department>> level2IdsMap = new HashMap<>();
        build(allDepartment, id2levelMap, level2IdsMap);

        List<Department> departments = new ArrayList<>();
        Integer level = id2levelMap.get(id);
        level2IdsMap.entrySet().stream().forEach(entry -> {
            Integer key = entry.getKey();
            List<Department> value = entry.getValue();
            if (key > level) {
                departments.addAll(value);
            }
        });
        return departments;
    }

    private static void build(List<Department> allDepartment,
                              Map<Integer, Integer> id2levelMap,
                              Map<Integer, List<Department>> level2IdsMap) {
        Set<Integer> departmentIds = allDepartment.stream().map(Department::getId).collect(Collectors.toSet());
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> notRootSet = new HashSet<>();

        for (Department department : allDepartment) {
            if (department.getPid() > 0) {
                notRootSet.add(department.getId());
            }
        }
        int nowLevel = 1;

        for (Integer departmentId : departmentIds) {
            if (notRootSet.contains(departmentId)) {
                queue.offer(departmentId);
                id2levelMap.put(departmentId, nowLevel);
                add2map(level2IdsMap, nowLevel, allDepartment, departmentId);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            nowLevel = nowLevel + 1;

            for (int i = 0; i < size; i++) {
                Integer nowId = queue.poll();

                for (Department department : allDepartment) {
                    if (department.getPid() == nowId) {
                        int sonId = department.getId();
                        id2levelMap.put(sonId, nowLevel);
                        add2map(level2IdsMap, nowLevel, allDepartment, sonId);
                        queue.add(sonId);
                    }
                }
            }
        }
        return;
    }

    private static void add2map(Map<Integer, List<Department>> level2IdsMap, int nowLevel, List<Department> allDepartment, Integer departmentId) {
        if (level2IdsMap.containsKey(nowLevel)) {
            for (Department department : allDepartment) {
                if (department.getId() == departmentId) {
                    level2IdsMap.get(nowLevel).add(department);
                }
            }
        } else {
            ArrayList<Department> Lists = new ArrayList<>();
            for (Department department : allDepartment) {
                if (department.getId() == departmentId) {
                    Lists.add(department);
                }
            }
            level2IdsMap.put(nowLevel, Lists);
        }
    }
}

class Department {
    /**
     * id
     */
    private int id;
    /**
     * parent id
     */
    private int pid;
    /**
     * 名称
     */
    private String name;

    public Department(int id, int pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                '}';
    }
}