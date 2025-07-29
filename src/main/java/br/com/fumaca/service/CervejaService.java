package br.com.fumaca.service;

import br.com.fumaca.repository.CervejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CervejaService {

    @Autowired
    private CervejaRepository cervejaRepository;


}
