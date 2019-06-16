package com.masi.red.controller.api;

import com.masi.red.IUsabilityDataService;
import com.masi.red.entity.MetricScreenCap;
import com.masi.red.entity.UsabilityData;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    //TODO: GET CURRENT USERNAME AND SAVE IT AS PART OF FILENAME...
    @PostMapping(value="/screencap")
    public ResponseEntity<Object> captureScreen(@Valid @RequestBody MetricScreenCap capture) throws Exception {

        System.out.println(capture.toString());

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(capture.getWidth(),capture.getHeight());
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filedate = sdf.format(timestamp);

        String filename = filedate + "-" + capture.getStatus();

        ImageIO.write(image, "png", new File("screens/"+ filename + ".png"));

        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
