package org.jenkinsci.plugins.readme;
import hudson.Extension;
import hudson.model.*;

/**
 * �����O���D�n�\��O�z�L�~��Jenkins Extension��RootAction���X�RJenkins������
 * �D���A�æb��椤�[�J�@�Ӧۭq��Readme�s���C 
 * �{������ReadMeDescriptor�l���O�h�O�t�d��@Descriptor�A�D�n�\��O�ʸ˳B�z
 * global.jelly����ơA���ϥΪ̥i�H�bglobal configuration�����W�]�wReadme��URL��
 * �m�C
 * 
 * @author Sam Chiu
 */
@Extension
public class ReadMeRootAction implements RootAction, Describable<ReadMeRootAction> {

	/*
	 * index.jelly�N�|�z�L��method���oJenkins��PrimaryView�C
	 */
    public View getPrimaryView() {
        return Hudson.getInstance().getPrimaryView();
    }
    /*
     * ���w�s������ܦW��
     * @see hudson.model.Action#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return "Read Me";
    }
    /*
     * �ϥιw�]��help icon�C
     * @see hudson.model.Action#getIconFileName()
     */
    @Override
    public String getIconFileName() {
        return "help.png";
    }
    /*
     * �b����k�����wurl�C
     * @see hudson.model.Action#getUrlName()
     */
    @Override
    public String getUrlName() {
        return "/readme";
    }

    /*
     * getDescriptor���ͦ�Descriptor���ߤ@�ɭ��A�O�@��Singleton pattern�A�]�N�O��
     * ��ReadMeDescriptor���O�b�t�ΤW�u�|����@�ӭ���C����ReadMeDescriptor���󪺤覡
     * �O�z�LHudson����getDescriptor��k�A�öǤJ���ͦ������O�@���ѼơC
     * @see hudson.model.Describable#getDescriptor()
     */
    @Override
    public ReadMeDescriptor getDescriptor() {
        return (ReadMeDescriptor)Hudson.getInstance().getDescriptor(getClass());
    }

    /**
     * �����O���\��D�n�Ω��X�RJenkins global configuration�����A�ô��ѨϥΪ̥i�H���w
     * Readme URL�C
     */
    @Extension
    public static final class ReadMeDescriptor extends Descriptor<ReadMeRootAction> {
        // �]�w�w�]��ReadmeURL�A�w�]�ڭ̱Nreadme.html��m��Readme�ۨ�plugin���ؿ��U
        private String readMeUrl = "/plugin/Readme/readme.html";
        
        public ReadMeDescriptor() {
        	// �bclass���غc�l�I�sload()�i�H�q�w��Ū�����e�z�Lsave()�����
            load();
        }

        @Override
        public boolean configure(org.kohsuke.stapler.StaplerRequest req,
                    net.sf.json.JSONObject formData) throws FormException {
        	
        	// formData���@��JSONObject�AJelly��������ƫK�O�z�L���@����ʸ�,
        	// "readMeUrl"�h��global.jelly���ҹ�����data field
            readMeUrl = formData.getString("readMeUrl");
            save();
            return super.configure(req, formData);
        }
        
        /*
         * �мggetDisplayName�A�^�Ǫ��r��N��ܩ�Jenkins global configuration�]�w����
         * @see hudson.model.Descriptor#getDisplayName()
         */
        @Override
        public String getDisplayName() {
            return "Readme Configuration";
        }

        /*
         * get��k�Aindex.jelly���LgetReadMeUrl���oreadMeUrl�ܼơC
         */
        public String getReadMeUrl() {
            return readMeUrl;
        }

    }

}
