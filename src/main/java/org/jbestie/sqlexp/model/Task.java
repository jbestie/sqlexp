package org.jbestie.sqlexp.model;

/**
 * Task model
 *
 */
public class Task {
    private Long id;
    private Long category;
    private String name;
    private String description;
    private String query;

    public Task() {
    }

    public Task(Long id, Long category, String name, String description, String query) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.query = query;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!category.equals(task.category)) return false;
        if (!name.equals(task.name)) return false;
        if (!description.equals(task.description)) return false;
        return query.equals(task.query);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + query.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
