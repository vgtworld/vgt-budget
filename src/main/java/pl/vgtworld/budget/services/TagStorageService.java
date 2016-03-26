package pl.vgtworld.budget.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.tags.TagDto;
import pl.vgtworld.budget.storage.tags.Tag;
import pl.vgtworld.budget.storage.tags.TagDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TagStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TagStorageService.class);

	@EJB
	private TagDao tagDao;

	public TagDto createNewTag(TagDto tag) {
		LOGGER.debug("Create new tag: {}", tag);
		Tag entity = new Tag();
		entity.setName(tag.getName());
		tagDao.create(entity);
		return findByName(tag.getName());
	}

	public TagDto findByName(String name) {
		return asTagItem(tagDao.findByName(name));
	}

	public List<TagDto> findForProduct(int productId) {
		return tagDao.findForProduct(productId).stream().map(TagStorageService::asTagItem).collect(Collectors.toList());
	}

	public void deleteForProduct(int productId) {
		tagDao.deleteForProduct(productId);
	}

	public boolean existWithName(String name) {
		return findByName(name) != null;
	}

	private static TagDto asTagItem(Tag tag) {
		if (tag == null) {
			return null;
		}
		TagDto result = new TagDto();
		result.setId(tag.getId());
		result.setName(tag.getName());
		return result;
	}

}
