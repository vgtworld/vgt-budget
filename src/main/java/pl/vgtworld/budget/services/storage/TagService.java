package pl.vgtworld.budget.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.tags.NewTag;
import pl.vgtworld.budget.services.dto.tags.TagItem;
import pl.vgtworld.budget.storage.tags.Tag;
import pl.vgtworld.budget.storage.tags.TagDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TagService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TagService.class);

	@EJB
	private TagDao tagDao;

	public TagItem createNewTag(NewTag tag) {
		LOGGER.debug("Create new tag: {}", tag);
		Tag entity = new Tag();
		entity.setName(tag.getName());
		tagDao.create(entity);
		return findByName(tag.getName());
	}

	public TagItem findByName(String name) {
		return asTagItem(tagDao.findByName(name));
	}

	public boolean existWithName(String name) {
		return findByName(name) != null;
	}

	private static TagItem asTagItem(Tag tag) {
		if (tag == null) {
			return null;
		}
		TagItem result = new TagItem();
		result.setId(tag.getId());
		result.setName(tag.getName());
		return result;
	}

}
