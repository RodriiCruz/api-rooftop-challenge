package api.rooftop.challenge.service;

import api.rooftop.challenge.repository.ITextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Service
public class TextServiceImpl {

    @Autowired
    private ITextRepository repository;

}
