package com.eherrelaz;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name = "smells", defaultPhase = LifecyclePhase.VALIDATE)
public class Plugin extends AbstractMojo
{

    @Parameter(property = "smells.csv_path", defaultValue = "file.csv")
    String csvPath = "";
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            // System.out.println(csvPath);
            
            Process proc = Runtime.getRuntime()
                .exec("java -jar ./TestSmellDetector.jar " + csvPath + " -o output.html");

            StringBuilder sa = new StringBuilder();
            for (int ch; (ch = proc.getInputStream().read()) != -1; ) {
                sa.append((char) ch);
            }
            System.out.println(sa.toString());


            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = proc.getInputStream().read()) != -1; ) {
                sb.append((char) ch);
            }
            System.out.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
