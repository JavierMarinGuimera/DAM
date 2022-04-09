package service;

import pojos.Idiomes;

public interface LanguageService {
    public boolean createOne(Idiomes language);

    public Idiomes readOne(int id);

    public boolean deleteOne(int id);
}
