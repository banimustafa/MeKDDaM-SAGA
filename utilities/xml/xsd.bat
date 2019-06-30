echo off

set xsdFile="%1"
set outDirectory="%2"

set xsdExeDir="C:\Program Files\Microsoft Visual Studio 8\SDK\v2.0\Bin"
rem set language="VB"
set language="CS"

xsd.exe "%xsdFile%" /c /out:"%outDirectory%" /l:"%language%"