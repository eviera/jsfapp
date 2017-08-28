package net.eviera.prueba.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class FacesControllerUtil {


    public static String putRef(Object o) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
        References refs = (References) session.getAttribute("refs");
        if (refs == null) {
            refs = new References();
            session.setAttribute("refs", refs);
        }
        return refs.put(o);
    }

    public static Object getRef(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
        References refs = (References) session.getAttribute("refs");
        if (refs == null) {
            refs = new References();
            session.setAttribute("refs", refs);
        }
        return refs.get(key);
    }

}
