package br.com.frederico.neres.todolist.controller.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Response<T> {

    private T data;
    private List<String> Errors = new ArrayList<String>();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return Errors;
    }

    public void setErrors(List<String> errors) {
        Errors = errors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(data, response.data) && Objects.equals(Errors, response.Errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, Errors);
    }
}
