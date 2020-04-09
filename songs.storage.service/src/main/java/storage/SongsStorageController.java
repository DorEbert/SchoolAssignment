package storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.assertj.core.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongsStorageController {
	private Map<String, Map<String, Object>> storage;
	private AtomicLong idGenerator;

	@PostConstruct
	public void init() {
		this.idGenerator = new AtomicLong(1);
		storage = Collections.synchronizedMap(new TreeMap<String, Map<String, Object>>());
	}

	/*
	 * input: { "key":"value", "key2":true, "key3":12, "key4":12.0, "key5":{ } }
	 */
	@RequestMapping(path = "/songsstorage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectWithKey store(@RequestBody Map<String, Object> object) {
		// stub implementation
		String newKey = "" + this.idGenerator.getAndIncrement();
		ObjectWithKey rv = new ObjectWithKey(newKey, object);
		storage.put(newKey, object);
		return rv;
	}

	@RequestMapping(path = "/songsstorage/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable("id") String id, @RequestBody Map<String, Object> object) {
		storage.put(id, object);
	}

	@RequestMapping(path = "/songsstorage/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> get(@PathVariable("id") String key) {
		if (storage.get(key) != null)
			return storage.get(key);
		else
			throw new DataNotFoundException("could not find item by key: " + key);
	}

	@RequestMapping(path = "/songsstorage", method = RequestMethod.DELETE)
	public void clearAllData() {
		storage.clear();
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, Object> handleError(DataNotFoundException e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Item not found";
		}

		return Collections.singletonMap("error", message);
	}

	// GET
	// /songs/search?size={size}&page={page}&sortBy={sortAttribute}&sortOrder={order}

	@RequestMapping(
			// storage?size={size}&page={page}
			path = "/songsstorage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>[] getAllUsingPagination(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "songId") int sortAttribute,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "asc") int order) {

		int startPoint = size * page;
		return this.storage.values().stream().skip(startPoint).limit(size).collect(Collectors.toList())
				.toArray((Map<String, Object>[]) new Map[0]);
	}

	// GET
	// /songs/search?criteriaType=byName&criteriaValue={value}&size={size}&page={page}&sortBy={sortAttribute}&sortOrder={order}
	@RequestMapping(path = "/songsstorage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = {
			"criteriaType=byName" })
	public Map<String, Object>[] getSpecifiedNameWithPagination(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "songId") int sortAttribute,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "asc") int order,
			@RequestParam(name = "criteriaValue", required = true) int value) {

		int startPoint = size * page;
		return this.storage.values().stream().skip(startPoint).limit(size).collect(Collectors.toList())
				.toArray((Map<String, Object>[]) new Map[0]);
		// TODO: search by name.
	}

	// GET
	// /songs/search?criteriaType=byPerformer&criteriaValue={value}&size={size}&page={page}&sortBy={sortAttribute}&sortOrder={order}
	@RequestMapping(path = "/songsstorage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = {
			"criteriaType=byPerformer" })
	public Map<String, Object>[] getSpecifiedPerformerWithPagination(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "songId") int sortAttribute,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "asc") int order,
			@RequestParam(name = "criteriaValue", required = true) int value) {

		int startPoint = size * page;
		return this.storage.values().stream().skip(startPoint).limit(size).collect(Collectors.toList())
				.toArray((Map<String, Object>[]) new Map[0]);
		// TODO: search by Performer.
	}

	// GET
	// /songs/search?criteriaType=byGenre&criteriaValue={value}&size={size}&page={page}&sortBy={sortAttribute}&sortOrder={order}
	@RequestMapping(path = "/songsstorage/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = {
			"criteriaType=byGenre" })
	public Map<String, Object>[] getSpecifiedGenreWithPagination(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "songId") int sortAttribute,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "asc") int order,
			@RequestParam(name = "criteriaValue", required = true) int value) {

		int startPoint = size * page;
		return this.storage.values().stream().skip(startPoint).limit(size).collect(Collectors.toList())
				.toArray((Map<String, Object>[]) new Map[0]);
		// TODO: search by genre.
	}

}
