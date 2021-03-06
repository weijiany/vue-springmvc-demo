package smart.application.appoint.service;

import org.springframework.stereotype.Service;
import smart.application.appoint.dao.EquipmentTypeMapper;
import smart.application.appoint.models.EquipmentType;
import smart.application.appoint.util.BaseDao;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EquipmentTypeService implements EquipmentTypeMapper {

    @Resource
    private EquipmentTypeMapper equipmentTypeMapper;

    @Resource
    private BaseDao<EquipmentType> baseDao;

    @Override
    public List<EquipmentType> selectEquipmentTypeByPage(int page, int maxNumber) {
        return equipmentTypeMapper.selectEquipmentTypeByPage(page * maxNumber, maxNumber);
    }

    @Override
    public boolean existEquipmentTypeByName(String name) {
        return equipmentTypeMapper.existEquipmentTypeByName(name);
    }

    @Override
    public int countEquipmentType() {
        return equipmentTypeMapper.countEquipmentType();
    }

    @Override
    public EquipmentType selectEquipmentTypeById(int id) {
        return equipmentTypeMapper.selectEquipmentTypeById(id);
    }

    @Override
    public EquipmentType selectEquipmentTypeByName(String name) {
        return equipmentTypeMapper.selectEquipmentTypeByName(name);
    }

    @Override
    public void save(EquipmentType equipmentType) {
        equipmentTypeMapper.save(equipmentType);
    }

    @Override
    public void deleteById(int id) {
        equipmentTypeMapper.deleteById(id);
    }

    @Override
    public void update(EquipmentType equipmentType) {
        equipmentTypeMapper.update(equipmentType);
    }

    public String saveEquipmentType(EquipmentType equipmentType) {
        return baseDao.save(equipmentTypeMapper, equipmentType);
    }

    public String updateEquipmentType(EquipmentType equipmentType) {
        return baseDao.update(equipmentTypeMapper, equipmentType);
    }

    public String deleteEquipmentType(int id) {
        return baseDao.deleteById(equipmentTypeMapper, id);
    }
}
