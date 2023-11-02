package com.eherrelaz;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "smells", defaultPhase = LifecyclePhase.TEST)
public class Plugin extends AbstractMojo
{
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {

            String[] args = new String[1];
            args[0] = "file.csv";

            Main.main(args);
            
            Csv2HTML.main();
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
