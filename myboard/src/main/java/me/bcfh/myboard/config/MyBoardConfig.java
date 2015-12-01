package me.bcfh.myboard.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBoardConfig {

    private static final Logger log = LoggerFactory.getLogger("Configuration");

    public static final String WORKING_DIRECTORY_KEY = "workingDirectory";

    private String workingDirPath;

    private String parserLanguage;

    public MyBoardConfig(String workingDirPath) {
	super();
	this.workingDirPath = workingDirPath;
	log.info("-----------------------------------------------------------------");
	log.info("Using Working Directory {}", workingDirPath);
	log.info("Using Database File {}", getDatabaseFilePath().toFile().getAbsolutePath());
	log.info("Using Fulltext Index Directory {}", getFulltextIndexDirectory().toFile().getAbsolutePath());
	log.info("-----------------------------------------------------------------");
    }

    public Path getDatabaseFilePath() {
	Path path = Paths.get(workingDirPath, "database");
	ensurePathExists(path);
	path = Paths.get(path.toFile().getAbsolutePath(), "docappDB.sqlite");
	return path;
    }

    public Path getFulltextIndexDirectory() {
	Path path = Paths.get(workingDirPath, "fulltext_indexes");
	ensurePathExists(path);
	return path;
    }

    public String getWorkingDirPath() {
	return workingDirPath;
    }

    public void setWorkingDirPath(String workingDirPath) {
	this.workingDirPath = workingDirPath;
    }

    public String getParserLanguage() {
	return parserLanguage;
    }

    public void setParserLanguage(String parserLanguage) {
	this.parserLanguage = parserLanguage;
    }

    private void ensurePathExists(Path path) {
	if (!Files.exists(path)) {
	    try {
		Files.createDirectories(path);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
