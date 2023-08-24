package br.com.stocka.stockaspring.service.giftList;

import java.util.List;
import java.util.Optional;

import br.com.stocka.stockaspring.model.GiftListModel;

public interface GiftListService {
    public Long save(GiftListModel giftListModel);
    public boolean existsByDescription(String description);
    public Optional<GiftListModel> findByDescription(String description);
    public List<GiftListModel> findAll();
    public Optional<GiftListModel> findById(Long id);
    public void delete(GiftListModel giftListModel);
}
