# Geuron
Geuron is a hobby project of mine.

The intention is to train a convolutional neural network (CNN) on a small, user-selected set of thumbnails from an image search engine (Google to start with).
The CNN will then be tested on a much larger set of images from the image search engine, and try to pick out similar images.

Geuron is a Java [Spring Boot](http://projects.spring.io/spring-boot/) application, intended to be hosted on Tomcat 7.

I am developing this using a git-flow workflow. This means that master should contain stable releases, and develop is bleeding-edge.

The latest stable release is deployed at [apps.faithfull.me/geuron](http://apps.faithfull.me/geuron) via jenkins (If I haven't borked tomcat, which happens fairly often!).
