package edu.sdstate.eastweb.prototype.indices;

import java.io.File;

import edu.sdstate.eastweb.prototype.Config;
import edu.sdstate.eastweb.prototype.Python;
import edu.sdstate.eastweb.prototype.util.PythonHelper;

/**
 * LST calculator.
 * 
 * @author Isaiah Snell-Feikema
 */
public class LstCalculator implements IndexCalculator {
    private File workspace;
    private File daytime;
    private File nighttime;
    private File watermask;
    private File[] shapefiles;
    private File[] dayOutputs;
    private File[] meanOutputs;
    private File[] nightOutputs;

    public LstCalculator(File workspace, File daytime, File nighttime, File watermask,
            File[] shapefiles, File[] dayOutputs, File[] meanOutputs, File[] nightOutputs) {
        this.workspace = workspace;
        this.daytime = daytime;
        this.nighttime = nighttime;
        this.watermask = watermask;
        this.shapefiles = shapefiles;
        this.dayOutputs = dayOutputs;
        this.meanOutputs = meanOutputs;
        this.nightOutputs = nightOutputs;
    }

    @Override
    public void calculate() throws Exception {
        Python.run(
                "python/lst.py",
                Config.getInstance().getIndexPythonTimeout(),
                workspace.toString(),
                daytime.toString(),
                nighttime.toString(),
                watermask.toString(),
                PythonHelper.packParameters(shapefiles, ';'),
                PythonHelper.packParameters(dayOutputs, ';'),
                PythonHelper.packParameters(meanOutputs, ';'),
                PythonHelper.packParameters(nightOutputs, ';')
        );
    }

}
