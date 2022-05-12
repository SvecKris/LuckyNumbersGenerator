package com.dataLayer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseOfWinningNums {
	Path pathFileToRead = Paths.get("./Resources/winningNums.text");
	//Files.lines(pathFileToRead).map(String::toLowerCase).filter(str -> str.contains("a")).forEach(System.out::println);
}
