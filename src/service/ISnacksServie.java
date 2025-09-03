package service;

import domain.Snack;

import java.util.List;

public interface ISnacksServie {

    void addSnack(Snack snack);
    void showSnacks();
    List<Snack> getSnacks();


}
