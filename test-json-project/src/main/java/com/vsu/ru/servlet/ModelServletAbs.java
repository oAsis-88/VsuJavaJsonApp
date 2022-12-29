package com.vsu.ru.servlet;

import com.vsu.ru.model.DataBaseItem;
import com.vsu.ru.service.ModelService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class ModelServletAbs <K, T extends DataBaseItem<K>> extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if(split.length == 2){
            out.println(getModelService().getAsString(getModelService().getAll()));
        }else if(split.length == 3){
            K id = getModelService().getIdFromString(split[2]);
            out.println(getModelService().getAsString(getModelService().getById(id)));
        }else{
            resp.sendError(400);
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String stringModel = readDataFromRequest(req);
        List<T> list = getModelService().convertAllFromString(stringModel);
        getModelService().create(list);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String stringPlayer = readDataFromRequest(req);
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        if(split.length != 3){
            resp.sendError(400);
        }else{
            K id = getModelService().getIdFromString(split[2]);
            T one = getModelService().convertFromString(stringPlayer);
            one.setId(id);
            getModelService().create(one);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        if(split.length != 3){
            resp.sendError(400);
        }else{
            K id = getModelService().getIdFromString(split[2]);
            getModelService().deleteById(id);
        }
    }

    private String readDataFromRequest(HttpServletRequest req){
        StringBuilder jb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        return jb.toString();
    }

    protected abstract ModelService<K, T> getModelService();
}
