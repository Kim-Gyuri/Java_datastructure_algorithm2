package setup36;

import java.util.HashMap;
import java.util.LinkedList;

class Project {
    private String name;
    private LinkedList<Project> dependencies;
    private boolean marked;

    public Project(String name) {
        this.name = name;
        this.marked = false;
        this.dependencies = new LinkedList<Project>();
    }
    public void addDependency(Project project) {
        if (!dependencies.contains(project)) {
            dependencies.add(project);
        }
    }
    public LinkedList<Project> getDependencies() {
        return this.dependencies;
    }
    public String getName(){
        return this.name;
    }
    public void setMarked(boolean marked) {
        this.marked = marked;
    }
    public boolean getMarked() {
        return this.marked;
    }
    static class ProjectManager {
        private HashMap<String, Project> projects;
        public ProjectManager(String[] names, String[][] dependencies) {
            buildProjects(names);
            addDependencies(dependencies);
        }
        public void buildProjects(String[] names) {
            projects = new HashMap<String, Project>();
            for (String s : names) {
                projects.put(s, new Project(s));
            }
        }
        public void addDependencies(String[][] dependencies) {
            for (String[] dependency : dependencies) {
                Project before = findProject(dependency[0]);
                Project after = findProject(dependency[1]);
                after.addDependency(before);
            }
        }
        private int index;
        public Project[] buildOrder() {
            initMarkingFlags();
            Project[] ordered = new Project[this.projects.size()];
            index = 0;
            for (Project project : this.projects.values()) {
                buildOrder(project,ordered);
            }
            return ordered;
        }
        public void buildOrder(Project project, Project[] ordered) {
            if (!project.getDependencies().isEmpty()) {
                for (Project p : project.getDependencies()) {
                    buildOrder(p, ordered);
                }
            }
            if (!project.getMarked()) {
                project.setMarked(true);
                ordered[index] = project;
                index++;
            }
        }
        private void initMarkingFlags() {
            for (Project project : this.projects.values()) {
                project.setMarked(false);
            }
        }
        public Project findProject(String name) {
            return projects.get(name);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String[] projects = {"a", "b","c","d", "e", "f", "g"};
        String[][] dependencies = {{"f","a"}, {"f","b"},{"f","c"}, {"b","a"},
                {"c","a"},{"a","e"},{"b","e"},{"d", "g"}};
        Project.ProjectManager pm = new Project.ProjectManager(projects, dependencies);
        Project[] ps = pm.buildOrder();
        for (Project p : ps) {
            if (p != null) {
                System.out.print(p.getName() +" ");
            }
        }


    }
}
