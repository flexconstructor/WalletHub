package ru.flexconstructor.WalletHub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.BaseStream;

/**
 * {@link ParseFileService} implementation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ParseFileServiceImpl implements ParseFileService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<String> parseStream(String fileName) throws FileNotFoundException{
        Path path = Paths.get(fileName);
        if (!path.toFile().isFile()) {
            throw new FileNotFoundException(
                    "The file " + fileName + " doesn't exist or is not a text file");
        }
        return Flux.using(() -> Files.lines(path),
                Flux::fromStream,
                BaseStream::close
        );
    }
}
