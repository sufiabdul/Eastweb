package edu.sdstate.eastweb.prototype.indices;

import java.io.File;

import edu.sdstate.eastweb.prototype.Config;
import edu.sdstate.eastweb.prototype.Python;
import edu.sdstate.eastweb.prototype.util.PythonHelper;

/**
 * SAVI calculator.
 * 
 * @author Isaiah Snell-Feikema
 */
public class SaviCalculator implements IndexCalculator {
    private File workspace;
    private File red;
    private File nir;
    private File watermask;
    private File[] shapefiles;
    private File[] outputs;

    public SaviCalculator(File workspace, File red, File nir, File watermask, File[] shapefiles,
            File[] outputs) {
        this.workspace = workspace;
        this.red = red;
        this.nir = nir;
        this.watermask = watermask;
        this.shapefiles = shapefiles;
        this.outputs = outputs;
    }

    @Override
    public void calculate() throws Exception {
        Python.run(
                "python/savi.py",
                Config.getInstance().getIndexPythonTimeout(),
                workspace.toString(),
                red.toString(),
                nir.toString(),
                watermask.toString(),
                PythonHelper.packParameters(shapefiles, ';'),
                PythonHelper.packParameters(outputs, ';')
        );
    }
}
