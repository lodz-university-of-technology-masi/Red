package com.masi.red.config.converter;

import com.masi.red.common.Language;
import com.masi.red.dto.NewTestDTO;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class StringToNewTestDTOConverter implements Converter<String, NewTestDTO> {
    @Override
    public NewTestDTO convert(String source) {
        JSONObject jsonObject = new JSONObject(source);
        Integer editorId = null;
        if (jsonObject.has("editorId")) {
            editorId = Integer.valueOf(String.valueOf(jsonObject.get("editorId")));
        }
        Integer jobTitleId = Integer.valueOf(String.valueOf(jsonObject.get("jobTitleId")));
        String language = String.valueOf(jsonObject.get("language"));
        return new NewTestDTO(jobTitleId, editorId, Language.valueOf(language), null);
    }
}
