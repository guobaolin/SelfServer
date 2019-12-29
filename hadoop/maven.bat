@echo off
setlocal enabledelayedexpansion

if not exist "pom.xml" (
    echo 当前目录不存在maven文件pom.xml
    goto end
)

:showCommond
echo.
echo ? - 帮助。
echo q - 退出。
echo.
echo mc - 标准Maven命令：清理(clean)，删除构建目录target。
echo mt - 标准Maven命令：测试(test)，单元测试。
echo mp - 标准Maven命令：打包(package)，打包成jar、war等。
echo mi - 标准Maven命令：安装(install)，安装到本地Maven仓库。
echo md - 标准Maven命令：发布(deploy)，发布到远程Maven仓库。
echo.
echo vt - 版本管理：打Tag。
echo vp - 版本管理：发布Tag。
echo vr - 版本管理：回滚打Tag操作。
echo vb - 版本管理：打Branch。
echo vc - 版本管理：清理临时文件。
echo vs - 版本管理：修改工程版本（包括所有子模块）。
echo.

:selectCommond
set commond=
set /p commond=请输入操作命令：
if "%commond%" == "" goto selectCommond
if "%commond%" == "?" goto showCommond
if "%commond%" == "q" goto end
if "%commond%" == "mc" goto doClean
if "%commond%" == "mt" goto doTest
if "%commond%" == "mp" goto doPackage
if "%commond%" == "mi" goto doInstall
if "%commond%" == "md" goto doDeploy
if "%commond%" == "vc" goto doReleaseClean
if "%commond%" == "vt" goto doReleasePrepare
if "%commond%" == "vp" goto doReleasePerform
if "%commond%" == "vr" goto doReleaseRollback
if "%commond%" == "vb" goto doReleaseBranch
if "%commond%" == "vs" goto doSetVersion

echo ERROR: 选择错误，请重新选择。
pause > nul
echo.
goto showCommond



:end