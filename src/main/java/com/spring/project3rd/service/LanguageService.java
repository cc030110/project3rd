package com.spring.project3rd.service;

import com.spring.project3rd.domain.language.NeedLanguage;
import com.spring.project3rd.domain.language.NeedLanguageRepository;
import com.spring.project3rd.domain.language.UseLanguage;
import com.spring.project3rd.domain.language.UseLanguageRepository;
import com.spring.project3rd.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final UseLanguageRepository useLanguageRepository;
    private final NeedLanguageRepository needLanguageRepository;

    // 유저의 id와 language_code가 담겨있는 리스트를 받으면
    // repository에서 해당 id와 일치하는 모든 튜플들을 조회 후,
    // 받아온 코드 리스트에서 DB에 값이 없으면 추가, 있으면 그대로,
    // DB에는 있지만 받아온 코드 리스트에는 없을 경우 삭제
    // 따라서 join과 update 모두 가능

    @Transactional
    public void setUseLanguage(String id,List<String> useLang){
        List<UseLanguage> languageList = useLanguageRepository.findById(id);

        // 받아온 언어 코드가 db에 없을 경우 추가
        for(String code : useLang){
            boolean exist = false;
            // DB에서 받아온 리스트에서 확인
            for(UseLanguage dbData : languageList){
                if(code.equals(dbData.getLanguageCode())){
                    exist = true;
                    break;
                }
            }
            // id-code 일치하는 내용이 없으면 추가
            if(!exist){
                UseLanguage useLanguage = new UseLanguage(id,code);
                useLanguageRepository.save(useLanguage);
            }
        }

        // DB에는 있지만 받아온 언어코드에는 없을 경우 삭제
        for(UseLanguage dbData : languageList){
            if(!useLang.contains(dbData.getLanguageCode())){
                useLanguageRepository.delete(dbData);
            }
        }
    }

    @Transactional
    public void setNeedLang(String id,List<String> needLang){
        List<NeedLanguage> languageList = needLanguageRepository.findById(id);

        // 받아온 언어 코드가 db에 없을 경우 추가
        for(String code : needLang){
            boolean exist = false;
            // DB에서 받아온 리스트에서 확인
            for(NeedLanguage dbData : languageList){
                if(code.equals(dbData.getLanguageCode())){
                    exist = true;
                    break;
                }
            }
            // id-code 일치하는 내용이 없으면 추가
            if(!exist){
                NeedLanguage needLanguage = new NeedLanguage(id,code);
                needLanguageRepository.save(needLanguage);
            }
        }

        // DB에는 있지만 받아온 언어코드에는 없을 경우 삭제
        for(NeedLanguage dbData : languageList){
            if(!needLang.contains(dbData.getLanguageCode())){
                needLanguageRepository.delete(dbData);
            }
        }
    }


}
