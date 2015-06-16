package me.faithfull.geuron.test;

import me.faithfull.geuron.imagesearch.ImageService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
public class ImageServiceTest {

	@Autowired private ImageService imageService;
}
