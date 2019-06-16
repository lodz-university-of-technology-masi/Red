package com.masi.red;

import com.masi.red.entity.UsabilityData;
import com.masi.red.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsabilityDataService implements IUsabilityDataService {

    private final UsabilityDataRepository usabilityDataRepository;

    @Override
    public UsabilityData persist(UsabilityData usabilityData, User user) {

        int nextMeasurementNumber = findNextMeasurementNumber(user);
        usabilityData.setMeasurementNumber(nextMeasurementNumber);
        usabilityData.setUsername(user.getUsername());
        return usabilityDataRepository.save(usabilityData);
    }

    private int findNextMeasurementNumber(User user) {
        Optional<UsabilityData> lastUserUsabilityData =
                usabilityDataRepository.findTopByUsernameOrderByMeasurementNumberDesc(user.getUsername());
        int nextMeasurementNumber = 0;
        if(lastUserUsabilityData.isPresent()) {
            nextMeasurementNumber = lastUserUsabilityData.get().getMeasurementNumber() + 1;
        }
        return nextMeasurementNumber;
    }
}
