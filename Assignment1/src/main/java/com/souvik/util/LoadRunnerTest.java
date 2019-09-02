package com.souvik.util;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeMultiLoadRunner;
import org.junit.runner.RunWith;

import com.souvik.controller.MyController;

@LoadWith("load_config.properties")
@TestMappings({
    @TestMapping(testClass = MyController.class, testMethod = "sayName")/*,
    @TestMapping(testClass = MyController.class, testMethod = "sayAddress"),
    @TestMapping(testClass = MyController.class, testMethod = "sayHello")*/
})
@RunWith(ZeroCodeMultiLoadRunner.class)
public class LoadRunnerTest {

}
