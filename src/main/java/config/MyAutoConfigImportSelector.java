package config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {


    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
                // Annotation 클래스를 주고,
                // ClassLoader -> classPath 기준 자원을 읽어올 때 필요한 것
//        ImportCandidates candidates = ImportCandidates.load(MyAutoConfigImportSelector.class, classLoader);
//        Iterable<String> candidates1 = ImportCandidates.load(MyAutoConfigImportSelector.class, classLoader);
//        return StreamSupport.stream(candidates1.spliterator(), false).toArray(String[] :: new);
        List<String> autoConfigs = new ArrayList<>();

        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.stream().toArray(String[] :: new);
    };
}


