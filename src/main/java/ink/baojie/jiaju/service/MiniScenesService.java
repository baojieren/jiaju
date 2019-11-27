package ink.baojie.jiaju.service;

import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;

public interface MiniScenesService {

    BaseListOutDTO selectScenesAll();

    BaseOutDTO selectImgsByScenesId(int id);
}
