@echo off
setlocal
call "C:\Sun\SDK\config\asenv.bat"

rem ---------------------------------------------------------------------------
rem Copyright 2004-2005 Sun Microsystems, Inc.  All rights reserved.
rem Use is subject to license terms.
rem
rem Script to run XJC
rem ---------------------------------------------------------------------------

set JAVA_ENDORSED_DIRS=%AS_WEBSERVICES_LIB%\endorsed
rem Set CLASSPATH
set CLASSPATH=%AS_JAVA%\lib\tools.jar;%AS_INSTALL%\lib\appserv-jstl.jar;%AS_INSTALL%\lib\javaee.jar;%AS_INSTALL%\lib\appserv-ws.jar;%AS_WEBSERVICES_LIB%\commons-logging.jar;%AS_WEBSERVICES_LIB%\mail.jar;%AS_WEBSERVICES_LIB%\activation.jar;

"%AS_JAVA%\bin\java" -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.defaultlog=warn -Djava.endorsed.dirs="%JAVA_ENDORSED_DIRS%" -cp "%CLASSPATH%" com.sun.tools.xjc.Driver %*
endlocal
