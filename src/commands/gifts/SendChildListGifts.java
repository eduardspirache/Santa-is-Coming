package commands.gifts;

import children.Child;
import commands.Command;
import main.ConvertToJSONObj;
import northpole.Gift;
import northpole.Santa;
import northpole.elves.Elf;
import northpole.elves.ElfFactory;
import northpole.elves.YellowElf;
import org.json.simple.JSONArray;

import java.util.List;

import static common.Constants.YELLOW_ELF;

public class SendChildListGifts implements Command {
    private final List<Child> childList;
    private final Santa santa;
    JSONArray childReceivedGiftsJSON;

    public SendChildListGifts(List<Child> childList) {
        this.childList = childList;
        this.santa = Santa.getInstance();
        this.childReceivedGiftsJSON = new JSONArray();
    }

    public JSONArray getChildReceivedGiftsJSON() {
        return childReceivedGiftsJSON;
    }

    /**
     * Helper function for gift strategies
     * Iterates through the child list and returns an array with all the children that
     * received gifts in that round.
     */
    @Override
    public void execute() {
        for (Child child : childList) {
            SendGifts sendGifts = new SendGifts(child, santa.getBudgetList().get(child.getId()),
                    santa.getGiftList());
            sendGifts.execute();
            List<Gift> receivedGifts = sendGifts.getReceivedGifts();

            // Apply changes for Yellow Elf if the child's gift list is empty
            if (sendGifts.getReceivedGifts().size() == 0 && child.getElf().equals(YELLOW_ELF)) {
                Elf elf = ElfFactory.createElf(child, santa.getGiftList(),
                        santa.getBudgetList().get(child.getId()));
                elf.execute();
                receivedGifts.add(elf.getGift());
            }

            ConvertToJSONObj jsonObj = new ConvertToJSONObj(child, receivedGifts);
            jsonObj.execute();
            childReceivedGiftsJSON.add(jsonObj.getJsonChild());
        }
    }
}
