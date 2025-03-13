package mapper;

import ChildDTO.ChildDTO;
import ChildDTO.NewChildDTO;
import model.Child;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChildMapper {
    ChildMapper INSTANCE = Mappers.getMapper(ChildMapper.class);
ChildDTO childToChildDTO(Child child);
Child childDTOToChild(ChildDTO childDTO);
Child newChildDTOtoChild(NewChildDTO newChildDTO);

}
