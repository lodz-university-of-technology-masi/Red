package com.masi.red.controller.api;

import com.masi.red.IUsabilityDataService;
<<<<<<< HEAD
=======
import com.masi.red.entity.MetricScreenCap;
>>>>>>> 08b53b377fc153f2ba13ee374c10ab3af3970cd7
import com.masi.red.entity.UsabilityData;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
>>>>>>> 08b53b377fc153f2ba13ee374c10ab3af3970cd7
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import javax.validation.Valid;
=======
import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
>>>>>>> 08b53b377fc153f2ba13ee374c10ab3af3970cd7

@RestController
@RequestMapping("/api/usabilityData")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsabilityDataController {

    private final IUsabilityDataService usabilityDataService;

    @PostMapping
    public ResponseEntity<UsabilityData> persist(@Valid @RequestBody UsabilityData usabilityData,
                                                 @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(usabilityDataService.persist(usabilityData, user));
    }

    static {

        System.setProperty("java.awt.headless", "false");
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");

    @PostMapping(value="/screencap")
    public ResponseEntity<Object> captureScreen(@Valid @RequestBody MetricScreenCap capture) throws Exception {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(capture.getWidth(),capture.getHeight());
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filedate = sdf.format(timestamp);

        String filename = username + "-" + filedate;

        ImageIO.write(image, "png", new File("screens/"+ filename + ".png"));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
