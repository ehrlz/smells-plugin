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

            String[] args = new String[1];
            args[0] = "file.csv";

            Main.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
