package ru.flexconstructor.WalletHub.services;

import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;

/**
 * Parses access log files.
 */
public interface ParseFileService {

    /**
     * Parses files in non-blocking manner.
     *
     * @param path path to file.
     *
     * @return {@link Flux<String>} log lines stream.
     *
     * @throws FileNotFoundException excepts if file not found.
     */
    Flux<String> parseStream(String path) throws FileNotFoundException;

}
