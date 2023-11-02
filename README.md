# SMELLs-PLUGIN

## Installation

### Download to your local repository (.m2)

Get the lastest release of this respository:

``` bash
wget https://github.com/ehrlz/smells-plugin/releases/download/v0.1-first/smells-plugin-0.1.jar
```

Install it in the local repository:

``` bash
mvn install:install-file -Dfile=smells-plugin-0.1.jar -DgroupId=com.eherrelaz -DartifactId=smells-plugin -Dversion=0.1 -Dpackaging=jar
```

### Add to *pom.xml*

Add to `pom.xml`:

``` xml
<build>
    <plugins>
    
    ...

        <plugin>
            <groupId>com.eherrelaz</groupId>
            <artifactId>smells-plugin</artifactId>
            <version>0.1</version>
            <configuration>
                <csv_path>file.csv</csv_path>
            </configuration>
        </plugin>

    ...

    </plugins>
</build>
```

## Execution

`mvn com.eherrelaz:smells-plugin:smells`

> Must be a csv file called `file.csv` in the **root** of the project dir.
