package storage;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


public interface StorageCrud extends PagingAndSortingRepository<Map<String,Object>,String>{

	public List<Map<String,Object>> findAllById(@Param("Id") String id, Pageable pageable);
	
	public List<Map<String,Object>> findAllByName(@Param("name") String name, Pageable pageable);

	public List<Map<String,Object>> findAllByPerformer(@Param("performer") String performer, Pageable pageable);

	public List<Map<String,Object>> findAllByGenre(@Param("genre") String genre, Pageable pageable);
	
}
