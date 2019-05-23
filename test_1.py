import unittest

import model

"""
（1）测试文件必须先import unittest

（2）测试类必须继承unittest.TestCase

（3）测试方法必须以“test_”开头

（4）测试类必须要有unittest.main()方法
"""


class MyTest(unittest.TestCase):  # 继承unittest.TestCase
    def tearDown(self):
        # 每个测试用例执行之后做操作
        print('111')

    def setUp(self):
        # 每个测试用例执行之前做操作
        print('22222')

    @classmethod
    def tearDownClass(self):
        # 必须使用 @ classmethod装饰器, 所有test运行完后运行一次
        print('4444444')

    @classmethod
    def setUpClass(self):
        # 必须使用@classmethod 装饰器,所有test运行前运行一次
        print('33333')

    def test_a_run(self):
        self.assertEqual(1, model.inc(0))  # 测试用例

    def test_b_run(self):
        self.assertEqual(2, 2)  # 测试用例


if __name__ == '__main__':
    unittest.main()