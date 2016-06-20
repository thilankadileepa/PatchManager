package it.codegen.controllers;

import it.codegen.PatchManager;
import it.codegen.criteria.PatchSearchCriteria;
import it.codegen.data.Patch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by thilanka on 5/9/2016.
 */
@Controller
public class DefaultController
{
    @Autowired PatchManager svnPatchManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {

        PatchSearchCriteria criteria = new PatchSearchCriteria();
        List<Patch> patches = svnPatchManager.searchPatches( criteria );

        map.put("msg", "Hello Spring 4 Web MVC!" + patches.size());
        return "index";
    }
}
