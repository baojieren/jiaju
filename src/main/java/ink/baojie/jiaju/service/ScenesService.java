package ink.baojie.jiaju.service;

import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.service.dto.SaveScenesInDTO;
import ink.baojie.jiaju.service.dto.SelectScenesListInDTO;

public interface ScenesService {

    BaseOutDTO saveAndUpdateScenes(SaveScenesInDTO inDTO,String type);

    BaseListOutDTO selectPageList(SelectScenesListInDTO inDTO);

    BaseOutDTO selectOne(Integer id);

    BaseOutDTO changeValid(int id,int valid);
}
