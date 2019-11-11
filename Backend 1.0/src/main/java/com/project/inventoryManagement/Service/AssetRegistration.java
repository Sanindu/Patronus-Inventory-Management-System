package com.project.inventoryManagement.Service;

import com.project.inventoryManagement.Models.AssetModel;
import com.project.inventoryManagement.Repositories.AssetRepository;
import com.project.inventoryManagement.Repositories.IDNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetRegistration {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    private IDNumberRepo idNumberRepo;


    //////////////////////////////////////////////////////////////////////////////////////////////
    // Asset registration
    public AssetModel registerAnAsset(AssetModel aa1) {

        //creating an aasset id according to the category

        System.out.println("Asset passed again: "+ aa1.toString());

        System.out.println("line0");

        if(aa1.getAssetcategory().equals("Computer")){

            if(aa1.getCategoryTypes().equals("Laptop")){
                aa1.setAssetId("LAP");
            }else if(aa1.getCategoryTypes().equals("PC")){
                aa1.setAssetId("DPC");
            }

        }else if(aa1.getAssetcategory().equals("UPS")){
            aa1.setAssetId("UPS");
        }else if(aa1.getAssetcategory().equals("Projector")){
            aa1.setAssetId("PRJ");
        }else if(aa1.getAssetcategory().equals("SoundSystem")){
            aa1.setAssetId("SND");
        }else if(aa1.getAssetcategory().equals("tv")){
            aa1.setAssetId("TLV");
        }else if(aa1.getAssetcategory().equals("router")){
            aa1.setAssetId("RTR");
        } else if(aa1.getAssetcategory().startsWith("Fur") || aa1.getAssetcategory().startsWith("fur")){
            aa1.setAssetId("FUR");
        }else {
            aa1.setAssetId("OTH");
        }

        System.out.println("line00");
        aa1.toString();

        long oldId = idNumberRepo.findFirst().getAssetIdNumber();
        System.out.println("line111");
        aa1.setAssetId(aa1.getAssetId()+oldId);
        System.out.println("line222");
        int x = idNumberRepo.updateAssetId(oldId,++oldId);
        System.out.println("line333");

        aa1.setBroken(false);


        System.out.println("line1444");
        return assetRepository.saveAndFlush(aa1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
