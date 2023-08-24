package br.com.stocka.stockaspring.service.giftList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.stocka.stockaspring.model.GiftListModel;
import br.com.stocka.stockaspring.repository.GiftListRepository;
import jakarta.transaction.Transactional;

@Service
public class GiftListServiceImpl implements GiftListService {

    final GiftListRepository giftListRepository;

    public GiftListServiceImpl(GiftListRepository giftListRepository) {
        this.giftListRepository = giftListRepository;
    }

    @Transactional
    @Override
    public Long save(GiftListModel giftListModel) {
        GiftListModel giftListCreated = giftListRepository.save(giftListModel);
        return giftListCreated.getGiftListId();
    }

    @Override
    public boolean existsByDescription(String description) {
        return giftListRepository.existsByDescription(description);
    }

    @Override
    public Optional<GiftListModel> findByDescription(String description) {
        return giftListRepository.findByDescription(description);
    }

    @Override
    public List<GiftListModel> findAll() {
        return giftListRepository.findAll();
    }

    @Override
    public Optional<GiftListModel> findById(Long id) {
        return giftListRepository.findById(id);
    }

    @Transactional
    @Override
    public void delete(GiftListModel giftListModel) {
        giftListRepository.delete(giftListModel);
    }

}
