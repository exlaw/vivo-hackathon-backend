# coding=utf8
import os
import time


def findTestWithPath():
    current_dir = os.getcwd()
    folderName = os.listdir(current_dir)
    # print folderName
    # 获取到测试文件所在目录
    TestSuit = [suite for suite in folderName if not suite.find("TestSuit")]
    # 用来保存测试文件
    testfile = []
    withPathFile = []
    for suite in TestSuit:
        # 获取测试目录下的所有测试文件
        testfile = testfile + os.listdir(".\\" + suite)
        for withPath in testfile:
            withPath = current_dir + "\\" + suite + "\\" + withPath
            withPathFile.append(withPath)
    del testfile
    # 把testfile中的py文件挑选出来
    withPathFile = [name for name in withPathFile if not "pyc" in name]
    # print testfile
    print(withPathFile)
    return withPathFile


def codeCoverage():
    now = time.strftime("%Y%m%d%H%M")
    htmlReport = os.getcwd() + "\\" + "CoverageReport"
    htmlCmd = "coverage html -d  " + htmlReport + "\\" + now
    for pyfile in findTestWithPath():
        runPyCmd = "coverage run " + pyfile
        if os.path.exists(htmlReport):
            os.system(runPyCmd)
            os.system(htmlCmd)
        else:
            os.mkdir(htmlReport)
            os.system(runPyCmd)
            os.system(htmlCmd)


if __name__ == "__main__":
    codeCoverage()