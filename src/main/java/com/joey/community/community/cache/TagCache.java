package com.joey.community.community.cache;

import com.joey.community.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {

    private TagCache() {

    }

    static{
        TagInstance.init();
    }

    public static List<TagDTO> get() {
       return TagInstance.tagDTOS;
    }

    private static class TagInstance {
        private static List<TagDTO> tagDTOS = new ArrayList<>();

        public static List<TagDTO> init() {
            TagDTO pigGrame = new TagDTO();
            pigGrame.setCategoryName("Pig Skill");
            pigGrame.setTags(Arrays.asList("变猪小技巧","养猪秘诀","降猪十八招","乾坤大挪猪","九猪真经","天山咸猪手"));

            tagDTOS.add(pigGrame);

            TagDTO beautyFrame = new TagDTO();
            beautyFrame.setCategoryName("Become More Beautiful");
            beautyFrame.setTags(Arrays.asList("变美小技巧","美妆达人","小姐姐是怎么炼成的","明星的速成法"));

            tagDTOS.add(beautyFrame);

            TagDTO playFrame = new TagDTO();
            playFrame.setCategoryName("Become More Handsome");
            playFrame.setTags(Arrays.asList("变帅小技巧","穿衣风格介绍","小哥哥是怎么炼成的","明星的速成法"));

            tagDTOS.add(playFrame);

            return tagDTOS;
        }
    }

    public static String isInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");

        List<String> collect = TagInstance.tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());

        List<String> validTag = Arrays.stream(split).filter(tag -> !collect.contains(tag)).collect(Collectors.toList());

        if(validTag.size() != 0) {
            return validTag.toString();
        }
        return "";
    }
}
