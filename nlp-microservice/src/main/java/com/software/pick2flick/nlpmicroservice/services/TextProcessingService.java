package com.software.pick2flick.nlpmicroservice.services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TextProcessingService {
    private Properties properties;

    private StanfordCoreNLP pipeline;

    {
        properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);
    }

    public List<String> findKeywords(List<String> descriptions) {
        System.out.println("Пришёл сюда");
        String totalDescription = String.join(". ", descriptions);
        
        Annotation document = new Annotation(totalDescription);

        pipeline.annotate(document);

        Map<String, Integer> keywords = new HashMap<>();

        findKeywords(keywords, document);
        findKeyPhrases(keywords, document);

        List<Map.Entry<String, Integer>> listOfKeywords = sortKeyWordsAndPhrases(keywords);

        return listOfKeywords.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    
    public Map<String, Integer> findKeywords(Map<String, Integer> keywords, Annotation document) {
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);

                if (pos.startsWith("NN") || pos.startsWith("NNS") || pos.startsWith("NNP") || pos.startsWith("NNPS")) {
                    keywords.put(lemma, keywords.getOrDefault(lemma, 0) + 1);
                }
            }
        }

        return keywords;
    }

    public Map<String, Integer> findKeyPhrases(Map<String, Integer> keywords, Annotation document) {
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            List<String> phrase = new ArrayList<>();
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);

                if (pos.startsWith("NN") || pos.startsWith("JJ") || pos.startsWith("NNS") || pos.startsWith("NNP")
                        || pos.startsWith("NNPS")) {
                    phrase.add(lemma);
                } else {
                    if (phrase.size() > 1) {
                        String keyPhrase = String.join(" ", phrase);
                        keywords.put(keyPhrase, keywords.getOrDefault(keyPhrase, 0) + 1);
                        phrase.clear();
                    } else {
                        phrase.clear();
                    }
                }
            }
        }

        return keywords;
    }

    public List<Map.Entry<String, Integer>> sortKeyWordsAndPhrases(Map<String, Integer> keyWordsAndPhrases) {
        List<Map.Entry<String, Integer>> listOfKeyWordsAndPhrases = new ArrayList<>(keyWordsAndPhrases.entrySet());

        listOfKeyWordsAndPhrases.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        return listOfKeyWordsAndPhrases;
    }
}