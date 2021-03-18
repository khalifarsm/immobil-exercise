package service;

import org.junit.Assert;
import org.junit.Test;

public class ContainerFillerTest {

    @Test
    public void testGetWorkTime() {
        ContainerFiller containerFiller = new ContainerFiller();
        int[] volumes = new int[]{5, 10, 30, 20, 15};
        Assert.assertEquals(containerFiller.getMinimumWorkTime(volumes), 35);
    }

}
