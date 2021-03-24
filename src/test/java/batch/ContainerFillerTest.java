package batch;

import org.junit.Assert;
import org.junit.Test;

public class ContainerFillerTest {

    @Test
    public void testGetWorkTime() {
        int[] volumes = new int[]{5, 10, 30, 20, 15};
        ContainerFiller containerFiller = new ContainerFiller(volumes, 3);
        containerFiller.init();
        Assert.assertEquals(containerFiller.getMinimumWorkTime(), 35);
    }

    @Test
    public void testGetWorkTime2() {
        int[] volumes = new int[]{10, 20, 30, 40};
        ContainerFiller containerFiller = new ContainerFiller(volumes, 2);
        containerFiller.init();
        Assert.assertEquals(containerFiller.getMinimumWorkTime(), 60);
    }

    @Test
    public void testGetWorkTime3() {
        int[] volumes = new int[]{10, 20, 30, 40, 50, 40, 30, 20, 10};
        ContainerFiller containerFiller = new ContainerFiller(volumes, 4);
        containerFiller.init();
        Assert.assertEquals(containerFiller.getMinimumWorkTime(), 90);
    }

}
