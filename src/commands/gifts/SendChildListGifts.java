package commands.gifts;

import children.Child;
import commands.Command;
import main.ConvertToJSONObj;
import northpole.Gift;
import northpole.Santa;
import northpole.elves.Elf;
import northpole.elves.ElfFactory;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.Constants.YELLOW_ELF;

public final class SendChildListGifts implements Command {
    private final List<Child> childList;
    private final Santa santa;
    private JSONArray childReceivedGiftsJSON;

    public SendChildListGifts(final List<Child> childList) {
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
        List<Child> childReceivedGifts = new ArrayList<>();
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
            child.setReceivedRoundGifts(receivedGifts);
            childReceivedGifts.add(child);
        }
        // We sort the list ascending by ID
        childReceivedGifts.sort(Comparator.comparingInt(Child::getId));
        for (Child child : childReceivedGifts) {
            ConvertToJSONObj jsonObj = new ConvertToJSONObj(child);
            jsonObj.execute();
            childReceivedGiftsJSON.add(jsonObj.getJsonChild());
        }
    }

}
