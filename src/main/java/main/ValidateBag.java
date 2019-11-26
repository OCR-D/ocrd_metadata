/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.github.jscancella.domain.Bag;
import edu.kit.ocrd.workspace.BagItUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.LoggerFactory;

/**
 * create dependenicies with ./gradlew copyDependencies
 * add ocrd_metadata-*.jar to folder dependencies
 * add shell script validateBag.sh to folder
 * #!/bin/sh
 * java -cp $(echo *.jar | tr ' ' ':') main.ValidateBag $1 $2
 * usage:
 *   bash validateBag.sh folderWithBag LogLevel( one of debug,info,warn,error)
 * @author hartmann-v
 */
public class ValidateBag {

  public static void main(String[] args) {
    Level level = Level.WARN;
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    if (args.length > 1) {
      level = Level.toLevel(args[1]);
    }
    System.out.println(level);
    Logger rootLogger = loggerContext.getLogger("com");
    rootLogger.setLevel(level);
    rootLogger = loggerContext.getLogger("edu");
    rootLogger.setLevel(level);

    String pathToBag = "/tmp/test";

    if (args.length > 0) {
      rootLogger.info("Directory to check " + args[0]);
      pathToBag = args[0];
    }
    Path bagitPath = Paths.get(pathToBag);
    Bag bag = BagItUtil.readBag(bagitPath);
    System.out.println(args[0] + " ---> " +BagItUtil.validateBagit(bag));
  }
}
